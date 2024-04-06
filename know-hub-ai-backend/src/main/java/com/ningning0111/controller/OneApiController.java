package com.ningning0111.controller;

import com.ningning0111.common.ApplicationConstant;
import com.ningning0111.common.BaseResponse;
import com.ningning0111.common.ErrorCode;
import com.ningning0111.common.ResultUtils;
import com.ningning0111.model.dto.AddApiDTO;
import com.ningning0111.model.dto.QueryApiDTO;
import com.ningning0111.service.OneApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Project: com.ningning0111.controller
 * @Author: pgthinker
 * @GitHub: https://github.com/ningning0111
 * @Date: 2024/4/2 17:42
 * @Description:
 */
@Tag(name="OneApiController",description = "One")
@RestController
@RequestMapping(ApplicationConstant.API_VERSION + "/one-api")
@RequiredArgsConstructor
public class OneApiController {
    private final OneApiService oneApiService;

    @Operation(summary = "add",description = "添加一个apikey")
    @PostMapping("/add")
    BaseResponse addOneApi(@RequestBody AddApiDTO request){
        String apiKey = request.apiKey();
        if(apiKey == null){
            return ResultUtils.error(ErrorCode.PARAMS_ERROR,"key is empty");
        }
        return oneApiService.addOneApi(request);
    }

    /**
     * 查询所有未禁止的key
     * @param queryApiRequest
     * @return
     */
    @Operation(summary = "select",description = "查询所有未禁止的key")
    @GetMapping("/select")
    BaseResponse selectApi(QueryApiDTO queryApiRequest){
        System.out.println(queryApiRequest.toString());
        return oneApiService.selectApi(PageRequest.of(queryApiRequest.page()-1, queryApiRequest.pageSize()));
    }

    /**
     * 禁止或解封
     * @param id
     * @return
     */
    @Operation(summary = "change",description = "禁止或解封")
    @PutMapping("/change/{id}")
    BaseResponse changeApi(@PathVariable("id") Long id){
        return oneApiService.changeApi(id);
    }

    /**
     * 根据id查找
     * @param id
     * @return
     */
    @Operation(summary = "selectById",description = "根据id查找")
    @GetMapping("/select/{id}")
    BaseResponse selectById(@PathVariable("id") Long id){
        return oneApiService.selectById(id);
    }

    /**
     * 通过id删除
     * @param id
     * @return
     */
    @Operation(summary = "deleteById",description = "通过id删除")
    @DeleteMapping("/delete/{id}")
    BaseResponse deleteById(@PathVariable("id") Long id){
        return oneApiService.deleteById(id);
    }

    /**
     * 通过id批量删除
     * @param ids
     * @return
     */
    @Operation(summary = "deleteByIds",description = "通过id批量删除")
    @DeleteMapping("/delete")
    BaseResponse deleteById(@RequestParam("ids") List<Long> ids){
        return oneApiService.deleteByIds(ids);
    }

}
