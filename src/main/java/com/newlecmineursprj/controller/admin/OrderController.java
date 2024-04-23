package com.newlecmineursprj.controller.admin;

import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.newlecmineursprj.entity.OrderView;
import com.newlecmineursprj.service.OrderService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequestMapping("admin/order")
@Controller("adminOrderController")
@RequiredArgsConstructor
public class OrderController {
    private static final String ORDER_VIEW = "/admin/order";

    private final OrderService service;

    @GetMapping("list")
    public String list(Model model, @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(required = false) String searchMethod,
            @RequestParam(defaultValue = "") String searchKeyword) {

        int count = service.getCount(searchMethod, searchKeyword.trim());

        List<OrderView> list = service.getList(page, searchMethod, searchKeyword);
        model.addAttribute("list", list);
        model.addAttribute("count", count);

        return ORDER_VIEW + "/list";
    }

    @GetMapping("excel")
    public void excel(HttpServletResponse response
                        ,@RequestParam(defaultValue = "0") List<Long> orderId) throws IOException{
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("게시판글들");
        int rowNo = 0;

        Row headerRow = sheet.createRow(rowNo++);
        headerRow.createCell(0).setCellValue("주문일시");
        headerRow.createCell(1).setCellValue("상품코드");
        headerRow.createCell(2).setCellValue("주문자");
        headerRow.createCell(3).setCellValue("상품명");
        headerRow.createCell(4).setCellValue("총금액");
        headerRow.createCell(5).setCellValue("실결제금액");
        headerRow.createCell(6).setCellValue("결제수단");
        headerRow.createCell(7).setCellValue("주문상태");

        for (Long id : orderId){
            OrderView orderView = service.getById(id);

            String productName = "";
            int productCount = orderView.getProductsCount();

            if(orderView.getProductNames().size()>0)
                productName = orderView.getProductNames().get(0);
            if(productCount > 1){
                productCount -= 1;
                productName = productName +  " 외 " + productCount + "개";
            }


            Row row = sheet.createRow(rowNo++);
            row.createCell(0).setCellValue(orderView.getOrderedDatetime());
            row.createCell(1).setCellValue(orderView.getCode());
            row.createCell(2).setCellValue(orderView.getUserName());
            row.createCell(3).setCellValue(productName);
            row.createCell(4).setCellValue(orderView.getTotalProductPrice());
            row.createCell(5).setCellValue(orderView.getTotalProductPrice() - orderView.getTotalDiscountAmount());
            row.createCell(6).setCellValue(orderView.getPaymentMethod());
            row.createCell(7).setCellValue(orderView.getOrderState());

        }

        response.setContentType("ms-vnd/excel");
        response.setHeader("Content-Disposition", "attachment;filename=boardlist.xlsx");
    
        workbook.write(response.getOutputStream());
        workbook.close();
    }
}
