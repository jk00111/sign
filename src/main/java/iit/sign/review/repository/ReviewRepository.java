package iit.sign.review.repository;

import iit.sign.review.entity.Review;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository {

    Review findOne(long id);

    Review findBySignId(long signId);

    void create(Review review);

    void update(Review review);

}
