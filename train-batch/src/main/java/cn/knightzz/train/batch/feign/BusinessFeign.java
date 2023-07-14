package cn.knightzz.train.batch.feign;

import cn.knightzz.train.common.response.CommonResp;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Date;

// @FeignClient("business")
//@FeignClient(name = "business", url = "http://127.0.0.1:8003/business")
@FeignClient(name = "business", url = "http://127.0.0.1:8003/train-batch")
public interface BusinessFeign {

    @GetMapping("/hello")
    String hello();

    @GetMapping("/admin/daily-train/gen-daily/{date}")
    CommonResp<Object> genDaily(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date date);
}
