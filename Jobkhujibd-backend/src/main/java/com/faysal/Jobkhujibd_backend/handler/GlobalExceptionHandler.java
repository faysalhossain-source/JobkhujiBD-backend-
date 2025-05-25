//package com.faysal.Jobkhujibd_backend.handler;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.multipart.MaxUploadSizeExceededException;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
//@ControllerAdvice
//public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
//
//    @ExceptionHandler(ApplicationException.class)
//    public ResponseEntity<String> handleApplicationException(ApplicationException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }
//
//    protected ResponseEntity<Object> handleMaxUploadSizeExceededException(
//            MaxUploadSizeExceededException ex, WebRequest request) {
//        return ResponseEntity.badRequest().body("File too large!");
//    }
//}
