package com.example.restdemo2.specification;

import com.example.restdemo2.domain.Person;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class PersonSpecification implements Specification<Person> {
    private SearchCriteria criteria;

    public PersonSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    @Override
    public Predicate toPredicate(Root<Person> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder builder) {
        if (criteria.getOperation().equalsIgnoreCase(">=")) {
            return builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());

        } else if (criteria.getOperation().equalsIgnoreCase("<=")) {
            return builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());

        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        } else if(criteria.getOperation().equalsIgnoreCase(":=")) {
            return builder.equal(root.get(criteria.getKey()), criteria.getValue());
        }
        return null;
    }
}