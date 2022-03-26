package springfive.cms.domain.models;

// import lombok.AllArgsConstructor;
import lombok.Data;
// import lombok.NoArgsConstructor;

@Data
// @AllArgsConstructor
// @NoArgsConstructor
public class Review {
    
    public Review(String userId, String status) {
        this.userId = userId;
        this.status = status;
    }

    public Review() {
        this.userId = "";
        this.status = "";
    }

    String userId;

    String status;
}
