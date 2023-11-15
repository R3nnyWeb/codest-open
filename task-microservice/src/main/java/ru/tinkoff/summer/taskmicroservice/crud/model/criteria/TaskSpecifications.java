package ru.tinkoff.summer.taskmicroservice.crud.model.criteria;


import org.springframework.data.jpa.domain.Specification;
import ru.tinkoff.summer.taskmicroservice.crud.model.Task;

public class TaskSpecifications {
    public static Specification<Task> searchByCriteria(TaskSearchCriteria criteria) {
        return (root, query, criteriaBuilder) -> {
            var predicate = criteriaBuilder.conjunction();

            if (criteria.getTitle() !=  null && !criteria.getTitle().isBlank()){
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.like(criteriaBuilder.lower(root.get("title")),
                                "%" + criteria.getTitle().toLowerCase().trim() + "%"));
            }
            if(criteria.getLevel() != null){
                predicate = criteriaBuilder.and(predicate,
                        criteriaBuilder.equal(root.get("level"),criteria.getLevel()));
            }
            predicate = criteriaBuilder.and(predicate,
                    criteriaBuilder.equal(root.get("isPublish"),criteria.isPublish()));
            return predicate;
        };
    }
}
