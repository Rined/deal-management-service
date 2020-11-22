package com.rined.proposal.feign;

import com.rined.proposal.model.dto.Template;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@RequestMapping("/api/v1")
@FeignClient(name = "template-client", url = "${x.template.service.url}")
public interface TemplateFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/templates/{templateId}")
    Template getTemplateById(@RequestHeader("X-UserId") String userId,
                             @RequestHeader("X-Username") String username,
                             @RequestHeader("X-UserEmail") String userEmail,
                             @PathVariable("templateId") String templateId);

}
