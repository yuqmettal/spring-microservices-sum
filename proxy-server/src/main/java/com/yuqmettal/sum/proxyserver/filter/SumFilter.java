package com.yuqmettal.sum.proxyserver.filter;

import org.apache.commons.codec.binary.Base64;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

import org.springframework.stereotype.Component;

@Component
public class SumFilter extends ZuulFilter {

    @Override
    public boolean shouldFilter() {
        return RequestContext.getCurrentContext().getRequest().getRequestURI().contains("sums");
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        String userName = getUserNameFromToken(ctx);

        Map<String, List<String>> newParameterMap = new HashMap<>();
        String authenticatedKey = "userName";
        String authenticatedValue = userName;
        newParameterMap.put(authenticatedKey,Arrays.asList(authenticatedValue));
        ctx.setRequestQueryParams(newParameterMap);
            
		return null;
	}

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 1;
    }
    
    private String getUserNameFromToken(RequestContext ctx){
        try {
            String payloadAuthorization = ctx.getRequest().getHeader("Authorization").replace("Bearer ", "").split("\\.")[1];
            String strPayload = new String(Base64.decodeBase64(payloadAuthorization));
            ObjectMapper mapper = new ObjectMapper();
            Map<String, String> map = new HashMap<>();
             map = mapper.readValue(strPayload, Map.class);
             return map.get("user_name");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

}
