package br.com.bruno.orange.mercadolivre.validacao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class UniqueValueValidator implements ConstraintValidator<UniqueValue, Object>{

	private String domain;
	private Class<?> klass;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void initialize(UniqueValue constraintAnnotation) {
		domain = constraintAnnotation.fieldName();
		klass = constraintAnnotation.domainClass();
		}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select 1 from "+klass.getName()+" where "+ domain+"=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		Assert.state(list.size() <=1, "Foi encontrado mais de um "+klass.getName()+" com atributo repetido");
		return list.isEmpty();
	}

}
