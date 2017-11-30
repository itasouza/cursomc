/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.projeto.estoque.cdm.api.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 *
 * @author Administrador
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAuth {
    
    private Boolean success;
    
    private String errorMessage;
    
    private AnswerAuth answer;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public AnswerAuth getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerAuth answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "AuthResponse{" + "success=" + success + ", errorMessage=" + errorMessage + ", answer=" + answer + '}';
    }
    
    
}
