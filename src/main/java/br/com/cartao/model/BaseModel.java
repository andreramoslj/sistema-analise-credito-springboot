package br.com.cartao.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * Created by andre on 15/03/20.
 */

@MappedSuperclass
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer"})
public abstract class BaseModel implements Serializable {
}
