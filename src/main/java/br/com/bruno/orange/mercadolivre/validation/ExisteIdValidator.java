package br.com.bruno.orange.mercadolivre.validation;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object>{

	private String domain;
	private Class<?> klass;
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void initialize(ExisteId constraintAnnotation) {
		domain = constraintAnnotation.fieldName();
		klass = constraintAnnotation.domainClass();
		}
	
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select 1 from "+klass.getName()+" where "+ domain+"=:value");
		query.setParameter("value", value);
		List<?> list = query.getResultList();
		Assert.isTrue(list.size() <=1, "aconteceu algo bizarro e você tem mais de um "+klass+" com o atributo "+domain+" com o valor = "+value);
		return !list.isEmpty();
	}

}
