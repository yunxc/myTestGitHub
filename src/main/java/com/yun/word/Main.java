/*
package com.yun.word;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.rtf.RtfWriter2;
import sun.misc.BASE64Decoder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.FileOutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

*/
/**
 * @author Administrator
 * @date 2019/9/19 18:32
 *//*

public class Main {

    public static void main(String[] args) throws Exception {
        List<Map<String, Object>> listclo = new ArrayList<>();
        List<Map<String, Object>> listdata  =new ArrayList<>();
        Map<String, Object> m1 = new HashMap();
        m1.put("FIELDNAME", "11111");
        m1.put("ASSOCIATIONNAME", "rrrrrr");
        listclo.add(m1);

        Map<String, Object> m2 = new HashMap();
        m2.put("ASSOCIATIONNAME", "kkkkkkk");
        listdata.add(m2);
        exportWordSingle(listclo, listdata);
    }

    public static void exportWordSingle(List<Map<String, Object>> listclo, List<Map<String, Object>> listdata) throws Exception {
        String name = listdata.get(0).get("病人姓名") + "";
        RtfWriter2 rtfWriter2 = null;
        try {
            // 创建word文档,并设置纸张的大小
            Document document = new Document(PageSize.A4);
            FileOutputStream out=new FileOutputStream("G:\\wordtest\\a.doc");
            rtfWriter2 = RtfWriter2.getInstance(document, out);
            document.open();

            Font f3 = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED), 13, Font.BOLD, new Color(0, 0, 0));
            Font f5 = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED), 13, Font.NORMAL, new Color(0, 0, 0));
            int size = listdata.size();
            for (int i = 0; i < size; i++) {
                Map<String, Object> data = listdata.get(i);
                //设置头
                Paragraph ph = new Paragraph();
                Font f = new Font();
                Paragraph p = new Paragraph("退出单病种结算申请表",
                        new Font(Font.NORMAL, 18, Font.BOLD, new Color(0, 0, 0)));
                p.setAlignment(1);
                document.add(p);
                ph.setFont(f);
                int columnSize = 4;
                Table table = new Table(columnSize);
                table.setBorderColor(Color.BLACK);
                table.setPadding(0);
                table.setSpacing(0);
                table.setWidth(100);
                int totalNum = listclo.size();
                boolean isEnd = false;
                for (int i1 = 0; i1 < totalNum; i1++) {
                    if (isEnd) {
                        break;
                    }
                    for (int j = 0; j < columnSize; j++) {
                        int num = (i1 * columnSize) + j;
                        if (num >= listclo.size()) {
                            int restNum = totalNum >= 4 ? (4 - totalNum % 4) : 4 - totalNum;
                            if (restNum != columnSize) {
                                for (int j2 = 0; j2 < restNum; j2++) {
                                    Cell cell = new Cell();
                                    cell.setRowspan(1);
                                    cell.setColspan(1);
                                    table.addCell(cell);
                                }
                            }
                            isEnd = true;
                            break;
                        }
                        Map<String, Object> col = listclo.get(num);
                        Paragraph key = new Paragraph(col.get("FIELDNAME") == null ? null :
                                col.get("FIELDNAME").toString());
                        key.setAlignment(1);
                        key.setFont(f3);
                        table.addCell(key);
                    }
                    for (int j1 = 0; j1 < columnSize; j1++) {
                        int num = (i1 * columnSize) + j1;
                        if (num >= listclo.size()) {
                            int restNum = totalNum >= 4 ? (4 - totalNum % 4) : 4 - totalNum;
                            if (restNum != columnSize) {
                                for (int j2 = 0; j2 < restNum; j2++) {
                                    Cell cell = new Cell();
                                    cell.setRowspan(1);
                                    cell.setColspan(1);
                                    table.addCell(cell);
                                }
                            }
                            isEnd = true;
                            break;
                        }
                        Map<String, Object> col = listclo.get(num);
                        Paragraph value = new Paragraph(data.get(col.get("ASSOCIATIONNAME")) == null ? null :
                                data.get(col.get("ASSOCIATIONNAME")).toString());
                        value.setAlignment(1);
                        value.setFont(f5);
                        table.addCell(value);
                    }
                }
                Paragraph reason = new Paragraph("申请退出依据:\n"
                        + (data.get("accord") == null ? "  " : data.get("accord").toString()));
                reason.setFont(f3);
                Cell cell = new Cell(reason);
                cell.setRowspan(25);
                cell.setColspan(4);
                cell.setBorderWidthBottom(0L);
                cell.setBorderWidthRight(10L);
                cell.setBorderWidthLeft(10L);
                table.addCell(cell);

                Cell cell0 = new Cell();
                Paragraph associationname = new Paragraph("附件:");
                associationname.setAlignment(3);
                associationname.setFont(f5);
                cell0.addElement(associationname);
                cell0.setRowspan(1);
                cell0.setColspan(4);
                cell0.setBorderWidth(1f);
                cell0.setBorderWidthTop(0);
                cell0.setBorderWidthRight(10L);
                cell0.setBorderWidthLeft(10L);
                cell0.setBorderWidthBottom(0L);
                table.addCell(cell0);

                if (data.get("fileList") != null) {
                    List<Map<String, Object>> imageList = (List) data.get("fileList");
                    for (Map<String, Object> image : imageList) {
                        Cell cell110 = new Cell();
                        Paragraph paragraph110 = new Paragraph("");
                        paragraph110.setAlignment(3);
                        paragraph110.setFont(f5);
                        cell0.addElement(paragraph110);
                        cell110.setColspan(4);
                        cell110.setBorderWidth(1f);
                        cell110.setBorderWidthTop(0);
                        cell110.setBorderWidthRight(10L);
                        cell110.setBorderWidthLeft(10L);
                        cell110.setBorderWidthBottom(10L);
                        String thumbUrl = image.get("thumbUrl").toString();
                        //添加图片base64的转码
                        String url = thumbUrl.replace("data:" + image.get("type").toString() + ";base64,", "");
                        com.lowagie.text.Image image1 = com.lowagie.text.Image.getInstance(generateImage(url));
                        image1.scaleAbsolute(cell110.getWidth(), cell110.getWidth());
                        cell110.add(image1);
                        table.addCell(cell110);
                    }
                }

                Cell cell1 = new Cell();
                Paragraph fieldnameys = new Paragraph("申请经办人："
                        + (data.get("applyPerson") == null ? "  " : data.get("applyPerson").toString()));
                fieldnameys.setAlignment(3);
                fieldnameys.setFont(f3);
                cell1.addElement(fieldnameys);
                cell1.setRowspan(1);
                cell1.setColspan(2);
                cell1.setBorderWidthTop(0L);
                cell1.setBorderWidthRight(10L);
                cell1.setBorderWidthLeft(10L);
                cell1.setBorderWidthBottom(10L);
                table.addCell(cell1);

                Cell cell2 = new Cell();
                Paragraph fieldnamesj = new Paragraph("申请时间："
                        + (data.get("applyDate") == null ? "  " : data.get("applyDate").toString()));
                fieldnamesj.setAlignment(3);
                fieldnamesj.setFont(f3);
                cell2.addElement(fieldnamesj);
                cell2.setRowspan(1);
                cell2.setColspan(2);
                cell2.setBorderWidthTop(0L);
                cell2.setBorderWidthRight(10L);
                cell2.setBorderWidthLeft(10L);
                cell2.setBorderWidthBottom(10L);
                table.addCell(cell2);

                Cell cell3 = new Cell();
                Paragraph shenhejg = new Paragraph("审核结果："
                        + (data.get("auditResult") == null ? "  " : data.get("auditResult").toString()));
                fieldnamesj.setAlignment(2);
                fieldnamesj.setFont(f3);
                cell3.addElement(shenhejg);
                cell3.setRowspan(5);
                cell3.setColspan(4);
                cell3.setBorderWidthTop(0L);
                cell3.setBorderWidthRight(10L);
                cell3.setBorderWidthLeft(10L);
                cell3.setBorderWidthBottom(10L);
                table.addCell(cell3);

                document.add(table);
                if (i != size - 1) {
                    document.newPage();
                }
            }
            rtfWriter2.flush();
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rtfWriter2 != null) {
                rtfWriter2.close();
            }
        }
    }


    public static void exportWordSingle(Map<String, Object> map, HttpServletRequest request, HttpServletResponse response,
                                        List<Map<String, Object>> listclo, List<Map<String, Object>> listdata) throws Exception {
        request.setCharacterEncoding("utf-8");
        response.setContentType("application/x-download");
        String name = listdata.get(0).get("病人姓名") + "";
        String fileName = URLEncoder.encode("退出医保定额结算病例-" + name + ".doc", "UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);

        RtfWriter2 rtfWriter2 = null;
        try {
            // 创建word文档,并设置纸张的大小
            Document document = new Document(PageSize.A4);
            ServletOutputStream out = response.getOutputStream();
            rtfWriter2 = RtfWriter2.getInstance(document, out);
            document.open();

            Font f3 = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED), 13, Font.BOLD, new Color(0, 0, 0));
            Font f5 = new Font(BaseFont.createFont("STSong-Light", "UniGB-UCS2-H",
                    BaseFont.NOT_EMBEDDED), 13, Font.NORMAL, new Color(0, 0, 0));
            int size = listdata.size();
            for (int i = 0; i < size; i++) {
                Map<String, Object> data = listdata.get(i);
                //设置头
                Paragraph ph = new Paragraph();
                Font f = new Font();
                Paragraph p = new Paragraph("退出单病种结算申请表",
                        new Font(Font.NORMAL, 18, Font.BOLD, new Color(0, 0, 0)));
                p.setAlignment(1);
                document.add(p);
                ph.setFont(f);
                int columnSize = 4;
                Table table = new Table(columnSize);
                table.setBorderColor(Color.BLACK);
                table.setPadding(0);
                table.setSpacing(0);
                table.setWidth(100);
                int totalNum = listclo.size();
                boolean isEnd = false;
                for (int i1 = 0; i1 < totalNum; i1++) {
                    if (isEnd) {
                        break;
                    }
                    for (int j = 0; j < columnSize; j++) {
                        int num = (i1 * columnSize) + j;
                        if (num >= listclo.size()) {
                            int restNum = totalNum >= 4 ? (4 - totalNum % 4) : 4 - totalNum;
                            if (restNum != columnSize) {
                                for (int j2 = 0; j2 < restNum; j2++) {
                                    Cell cell = new Cell();
                                    cell.setRowspan(1);
                                    cell.setColspan(1);
                                    table.addCell(cell);
                                }
                            }
                            isEnd = true;
                            break;
                        }
                        Map<String, Object> col = listclo.get(num);
                        Paragraph key = new Paragraph(col.get("FIELDNAME") == null ? null :
                                col.get("FIELDNAME").toString());
                        key.setAlignment(1);
                        key.setFont(f3);
                        table.addCell(key);
                    }
                    for (int j1 = 0; j1 < columnSize; j1++) {
                        int num = (i1 * columnSize) + j1;
                        if (num >= listclo.size()) {
                            int restNum = totalNum >= 4 ? (4 - totalNum % 4) : 4 - totalNum;
                            if (restNum != columnSize) {
                                for (int j2 = 0; j2 < restNum; j2++) {
                                    Cell cell = new Cell();
                                    cell.setRowspan(1);
                                    cell.setColspan(1);
                                    table.addCell(cell);
                                }
                            }
                            isEnd = true;
                            break;
                        }
                        Map<String, Object> col = listclo.get(num);
                        Paragraph value = new Paragraph(data.get(col.get("ASSOCIATIONNAME")) == null ? null :
                                data.get(col.get("ASSOCIATIONNAME")).toString());
                        value.setAlignment(1);
                        value.setFont(f5);
                        table.addCell(value);
                    }
                }
                Paragraph reason = new Paragraph("申请退出依据:\n"
                        + (data.get("accord") == null ? "  " : data.get("accord").toString()));
                reason.setFont(f3);
                Cell cell = new Cell(reason);
                cell.setRowspan(25);
                cell.setColspan(4);
                cell.setBorderWidthBottom(0L);
                cell.setBorderWidthRight(10L);
                cell.setBorderWidthLeft(10L);
                table.addCell(cell);

                Cell cell0 = new Cell();
                Paragraph associationname = new Paragraph("附件:");
                associationname.setAlignment(3);
                associationname.setFont(f5);
                cell0.addElement(associationname);
                cell0.setRowspan(1);
                cell0.setColspan(4);
                cell0.setBorderWidth(1f);
                cell0.setBorderWidthTop(0);
                cell0.setBorderWidthRight(10L);
                cell0.setBorderWidthLeft(10L);
                cell0.setBorderWidthBottom(0L);
                table.addCell(cell0);

                if (data.get("fileList") != null) {
                    List<Map<String, Object>> imageList = (List) data.get("fileList");
                    for (Map<String, Object> image : imageList) {
                        Cell cell110 = new Cell();
                        Paragraph paragraph110 = new Paragraph("");
                        paragraph110.setAlignment(3);
                        paragraph110.setFont(f5);
                        cell0.addElement(paragraph110);
                        cell110.setColspan(4);
                        cell110.setBorderWidth(1f);
                        cell110.setBorderWidthTop(0);
                        cell110.setBorderWidthRight(10L);
                        cell110.setBorderWidthLeft(10L);
                        cell110.setBorderWidthBottom(10L);
                        String thumbUrl = image.get("thumbUrl").toString();
                        //添加图片base64的转码
                        String url = thumbUrl.replace("data:" + image.get("type").toString() + ";base64,", "");
                        com.lowagie.text.Image image1 = com.lowagie.text.Image.getInstance(generateImage(url));
                        image1.scaleAbsolute(cell110.getWidth(), cell110.getWidth());
                        cell110.add(image1);
                        table.addCell(cell110);
                    }
                }

                Cell cell1 = new Cell();
                Paragraph fieldnameys = new Paragraph("申请经办人："
                        + (data.get("applyPerson") == null ? "  " : data.get("applyPerson").toString()));
                fieldnameys.setAlignment(3);
                fieldnameys.setFont(f3);
                cell1.addElement(fieldnameys);
                cell1.setRowspan(1);
                cell1.setColspan(2);
                cell1.setBorderWidthTop(0L);
                cell1.setBorderWidthRight(10L);
                cell1.setBorderWidthLeft(10L);
                cell1.setBorderWidthBottom(10L);
                table.addCell(cell1);

                Cell cell2 = new Cell();
                Paragraph fieldnamesj = new Paragraph("申请时间："
                        + (data.get("applyDate") == null ? "  " : data.get("applyDate").toString()));
                fieldnamesj.setAlignment(3);
                fieldnamesj.setFont(f3);
                cell2.addElement(fieldnamesj);
                cell2.setRowspan(1);
                cell2.setColspan(2);
                cell2.setBorderWidthTop(0L);
                cell2.setBorderWidthRight(10L);
                cell2.setBorderWidthLeft(10L);
                cell2.setBorderWidthBottom(10L);
                table.addCell(cell2);

                Cell cell3 = new Cell();
                Paragraph shenhejg = new Paragraph("审核结果："
                        + (data.get("auditResult") == null ? "  " : data.get("auditResult").toString()));
                fieldnamesj.setAlignment(2);
                fieldnamesj.setFont(f3);
                cell3.addElement(shenhejg);
                cell3.setRowspan(5);
                cell3.setColspan(4);
                cell3.setBorderWidthTop(0L);
                cell3.setBorderWidthRight(10L);
                cell3.setBorderWidthLeft(10L);
                cell3.setBorderWidthBottom(10L);
                table.addCell(cell3);

                document.add(table);
                if (i != size - 1) {
                    document.newPage();
                }
            }
            rtfWriter2.flush();
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rtfWriter2 != null) {
                rtfWriter2.close();
            }
        }
    }

    */
/**
     * 对字节数组字符串进行Base64解码并生成图片
     *
     * @param imgStr imgStr
     * @return 成图片
     *//*

    public static byte[] generateImage(String imgStr) {
        //图像数据为空
        if (imgStr == null) {
            return null;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }
            return b;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
*/
