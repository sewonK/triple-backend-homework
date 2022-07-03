package triple.backend.service;

import org.springframework.stereotype.Service;
import triple.backend.dto.EventRequest;
import triple.backend.enums.PointDetails;
import triple.backend.enums.PointType;

@Service
public class AddActionService implements ActionService {
    private final PointHistoryService pointHistoryServiceImpl;
    private final ReviewService reviewServiceImpl;

    public AddActionService(PointHistoryService pointHistoryServiceImpl, ReviewService reviewServiceImpl) {
        this.pointHistoryServiceImpl = pointHistoryServiceImpl;
        this.reviewServiceImpl = reviewServiceImpl;
    }

    @Override
    public int calculatePoints(EventRequest eventRequest) {
        int point = 0;
        for(PointDetails pointDetails: PointDetails.values()){
            if(checkCurrentReview(eventRequest, pointDetails)){
                point += pointHistoryServiceImpl.savePointHistory(eventRequest, PointType.PLUS, pointDetails);
            }
        }
        return point;
    }

    @Override
    public boolean checkCurrentReview(EventRequest eventRequest, PointDetails pointDetails) {
        if(pointDetails == PointDetails.TEXT) return eventRequest.getContent().length() > 0;
        else if(pointDetails == PointDetails.PHOTO) return eventRequest.getAttachedPhotoIds().size() > 0;
        else if(pointDetails == PointDetails.BONUS) return reviewServiceImpl.isFirstReview(eventRequest);
        return false;
    }
}
