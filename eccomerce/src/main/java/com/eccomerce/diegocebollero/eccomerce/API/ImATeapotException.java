package com.eccomerce.diegocebollero.eccomerce.API;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT, reason = "No element found")
public class ImATeapotException extends RuntimeException{

}
