package com.example.restdemo2.domain.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RESTResponse {
    private Map<String, Object> response;

    // MUST be private.
    private RESTResponse() {
        this.response = new HashMap<>();
    }

    public Map<String, Object> getResponse() {
        return response;
    }

    public void setResponse(Map<String, Object> response) {
        this.response = response;
    }

    public void addResponse(String key, Object value) {
        this.response.put(key, value);
    }

    public static class Error {

        private HashMap<String, String> errors;
        private int status;
        private String message;

        public Error() {
            this.errors = new HashMap<>();
            this.status = 0;
            this.message = "";
        }

        public Error setStatus(int status) {
            this.status = status;
            return this;
        }

        public Error setMessage(String message) {
            this.message = message;
            return this;
        }

        public Error addError(String key, String value) {
            this.errors.put(key, value);
            return this;
        }

        public Error addErrors(Map<String, String> errors) {
            this.errors.putAll(errors);
            return this;
        }

        public Map<String, Object> build() {
            RESTResponse restResponse = new RESTResponse();
            restResponse.addResponse("status", this.status);
            restResponse.addResponse("message", this.message);
            restResponse.addResponse("errors", this.errors);
            return restResponse.getResponse();
        }
    }

    public static class SimpleError {

        private int code;
        private String message;

        public SimpleError() {
            this.code = 0;
            this.message = "";
        }

        public SimpleError setCode(int code) {
            this.code = code;
            return this;
        }

        public SimpleError setMessage(String message) {
            this.message = message;
            return this;
        }

        public Map<String, Object> build() {
            RESTResponse restResponse = new RESTResponse();
            restResponse.addResponse("status", this.code);
            restResponse.addResponse("message", this.message);
            return restResponse.getResponse();
        }
    }


    public static class Success {

        private int status;
        private String message;
        private List<Object> datas;
        private RESTPagination pagination;


        public Success() {
            this.status = 1;
            this.message = "Thành công";
            this.datas = new ArrayList<>();
        }

        public Success setStatus(int status) {
            this.status = status;
            return this;
        }

        public Success setMessage(String message) {
            this.message = message;
            return this;
        }

        public Success setPagination(RESTPagination pagination) {
            this.pagination = pagination;
            return this;
        }

        public Success setData(Object obj) {
            this.datas.add(obj);
            return this;
        }

        public Success setDatas(List listObj) {
            this.datas.addAll(listObj);
            return this;
        }

        public int getStatus() {
            return status;
        }

        public String getMessage() {
            return message;
        }

        public List<Object> getDatas() {
            return datas;
        }

        public RESTPagination getPagination() {
            return pagination;
        }

        public Map<String, Object> build() {
            RESTResponse restResponse = new RESTResponse();
            restResponse.addResponse("status", this.status);
            restResponse.addResponse("message", this.message);
            if (this.datas.size() == 1) {
                restResponse.addResponse("data", this.datas.get(0));
            } else {
                restResponse.addResponse("data", this.datas);
            }
            if (this.pagination != null) {
                restResponse.addResponse("pagination", this.pagination);
            }
            return restResponse.getResponse();
        }

        public Map<String, Object> buildDatas() {
            RESTResponse restResponse = new RESTResponse();
            restResponse.addResponse("status", this.status);
            restResponse.addResponse("message", this.message);
            restResponse.addResponse("data", this.datas);
            if (this.pagination != null) {
                restResponse.addResponse("pagination", this.pagination);
            }
            return restResponse.getResponse();
        }

    }
}