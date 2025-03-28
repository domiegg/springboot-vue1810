package com.project.demo.controller;

import com.project.demo.entity.BiddingClassification;
import com.project.demo.service.BiddingClassificationService;
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
 * 招标分类：(BiddingClassification)表控制层
 *
 */
@RestController
@RequestMapping("/bidding_classification")
public class BiddingClassificationController extends BaseController<BiddingClassification, BiddingClassificationService> {

    /**
     * 招标分类对象
     */
    @Autowired
    public BiddingClassificationController(BiddingClassificationService service) {
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
