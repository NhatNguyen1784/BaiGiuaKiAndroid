# Branch mới nhất là main
# Hướng Dẫn Cấu Hình

## 1. Cấu Hình Database
Mở MySQL và thực hiện các lệnh sau để tạo tài khoản, database và cấp quyền:

```sql
CREATE DATABASE api DEFAULT CHARACTER SET utf8;
CREATE USER 'api'@'%.%.%.%' IDENTIFIED BY 'api!';
GRANT ALL PRIVILEGES ON api.* TO 'api'@'%.%.%.%';
```

## 2. Cấu Hình Network/Retrofit
- Nếu chạy API trên chính máy của bạn, đổi IP thành `localhost`.
- Nếu chạy API trên một máy khác, đổi IP thành địa chỉ của máy đó.

## 3. Cấu Hình Backend
Mở file `application.yaml` và cấu hình như sau:
- Đổi 2 dòng sau:
  ```yaml
  username: ${MAIL_USERNAME}
  password: ${MAIL_PASSWORD}
  ```
  **THÀNH**
  ```yaml
  username: email của tài khoản sẽ gửi mail
  password: mật khẩu ứng dụng của email đó sau khi tạo
  ```
- Đổi location của storage thành đường dẫn trên máy local mà mình muốn lưu:
  ```yaml
  storage:
    location: D:/workspace/Java/android/testAPI/uploads
  ```


## 4. Thành Viên & Phân Công Công Việc

| MSSV | Họ và Tên | Công việc |
|------|-----------|-----------|
| 22110405 | Lê Đào Nhân Sâm | Câu 1 |
| 22110295 | Đặng Đăng Duy | Câu 2 |
| 22110307 | Nguyễn Duy Đạt | Câu 3 |
| Cả Nhóm | | Câu 4 |
| 22110450 | Võ Văn Tuấn | Câu 5 |
| 21110912 | Nguyễn Hữu Phong | Câu 6 |
| 22110383 | Dương Khánh Nguyên | Câu 7 API Đăng ký, Đăng nhập |
| 22110383 | Nguyễn Nhật Nguyên | Câu 7 API Full Category, Last Product |
