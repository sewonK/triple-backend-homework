package triple.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import triple.backend.entity.PointHistory;
import triple.backend.entity.Review;
import triple.backend.enums.PointDetails;

import java.util.UUID;

@Repository
public interface PointHistoryRepository extends JpaRepository<PointHistory, UUID> {
    @Query(value = " SELECT SUM(p.point) as point "
            + " FROM point_history p "
            + " WHERE p.review = :review "
            + " and p.pointDetails = :pointDetails "
            + " group by p.review, p.pointDetails")
    PointHistory getPointByReviewAndPointDetails(@Param("review") Review review, @Param("pointDetails") PointDetails pointDetails);
}
