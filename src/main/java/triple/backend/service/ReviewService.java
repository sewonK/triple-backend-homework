package triple.backend.service;

import triple.backend.dto.EventRequest;
import triple.backend.entity.Review;

public interface ReviewService {
    Review findById(String reviewId);

    void saveReview(EventRequest eventRequest);

    void deleteReview(EventRequest eventRequest);
}
