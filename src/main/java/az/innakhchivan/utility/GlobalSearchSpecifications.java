package az.innakhchivan.utility;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class GlobalSearchSpecifications {
    public static <T> Specification<T> searchInFields(String keyword, String lang) {
        return (root, query, criteriaBuilder) -> {
            String titleField = lang + "Title";

            Predicate titlePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(titleField)),
                    "%" + keyword.toLowerCase() + "%"
            );

            return criteriaBuilder.or(titlePredicate);
        };
    }

    public static <T> Specification<T> searchInFieldsSector(String keyword, String lang) {
        return (root, query, criteriaBuilder) -> {
            String nameField = lang + "Description";

            Predicate namePredicate = criteriaBuilder.like(
                    criteriaBuilder.lower(root.get(nameField)),
                    "%" + keyword.toLowerCase() + "%"
            );

            return criteriaBuilder.or(namePredicate);
        };
    }
}
