    package vn.hcmute.entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Data;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    @Entity
    public class User {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(nullable = false)
        private String password;
    }
