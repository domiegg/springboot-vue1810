package com.project.demo.controller;

import com.project.demo.entity.TenderInformation;
import com.project.demo.service.TenderInformationService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;


/**
 * 投标信息：(TenderInformation)表控制层
 *
 */
@RestController
@RequestMapping("/tender_information")
public class TenderInformationController extends BaseController<TenderInformation, TenderInformationService> {

    /**
     * 投标信息对象
     */
    @Autowired
    public TenderInformationController(TenderInformationService service) {
        setService(service);
    }


    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
