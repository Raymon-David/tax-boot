package com.foryou.tax.process.fadada;

import com.fadada.lightapi.FDDSDKClient;
import com.fadada.lightapi.beans.contract.ContractDownloadRequest;
import com.fadada.lightapi.beans.contract.ContractDownloadResponse;
import com.foryou.tax.process.common.BaseProcess;
import com.foryou.tax.util.LoggerUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author ：Raymon
 * @date ：Created in 2020/10/19
 * @description: 法大大相关
 */
@Service
public class FadadaProcess extends BaseProcess {

    /**
     * 批量下载电子合同
     * @param request
     * @param response
     */
    public void contractBatchDownload(ArrayList contractList, HttpServletRequest request, HttpServletResponse response) {

        try {
            ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(zipOutputStream);

            response.setHeader("content-type", "application/octet-stream");
            response.setCharacterEncoding("UTF-8");
            /**
             * 设置浏览器响应头对应的Content-disposition
             * 参数中 合同文本 为压缩包文件名，尾部的.zip 为文件后缀
             */
            response.setHeader("Content-disposition",
                    "attachment;filename=" + new String("合同文本".getBytes("gbk"), "iso8859-1")+".zip");

            for(int i = 0; i < contractList.size(); i++){
                zipOutputStream.putNextEntry(new ZipEntry(contractList.get(i).toString() + ".pdf"));
                zipOutputStream.write(getViewContractUrl(contractList.get(i).toString()));
            }
            zipOutputStream.close();
            bufferedOutputStream.close();
        }catch (UnsupportedEncodingException ue){
            ue.printStackTrace();
            LoggerUtils.error(getClass(), ue.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
            LoggerUtils.error(getClass(), e.getMessage());
        }

    }

    /**
     * 下载完全盖章的pdf
     */
    public byte[] getViewContractUrl(String contentId) {
        FDDSDKClient client = new FDDSDKClient();
        ContractDownloadRequest req = new ContractDownloadRequest();
        ContractDownloadResponse rep = new ContractDownloadResponse();

        req.setContractId(contentId.toString());
        rep = client.contractDownload(req);

        if ("1000".equals(rep.getCode())) {
            return rep.getContent();
        } else {
            return null;
        }
    }
}
