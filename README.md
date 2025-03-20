# Hướng Dẫn Cấu Hình API

## 1. Cấu Hình MySQL
Mở MySQL và thực hiện các lệnh sau để tạo tài khoản, database và cấp quyền:

```sql
CREATE DATABASE api DEFAULT CHARACTER SET utf8;
CREATE USER 'api'@'%.%.%.%' IDENTIFIED BY 'api!';
GRANT ALL PRIVILEGES ON api.* TO 'api'@'%.%.%.%';
```

## 2. Cấu Hình Network/Retrofit
- Nếu chạy API trên chính máy của bạn, đổi IP thành `localhost`.
- Nếu chạy API trên một máy khác, đổi IP thành địa chỉ của máy đó.

## 3. Thành Viên & Phân Công Công Việc

| Câu hỏi | MSSV | Họ và Tên | Công việc |
|---------|------|-----------|-----------|
| Câu 1 | 22110405 | Lê Đào Nhân Sâm | |
| Câu 2 | 22110295 | Đặng Đăng Duy | |
| Câu 3 | 22110307 | Nguyễn Duy Đạt | |
| Câu 4 | Cả Nhóm | | |
| Câu 5 | 22110450 | Võ Văn Tuấn | |
| Câu 6 | 21110912 | Nguyễn Hữu Phong | |
| Câu 7 | 22110383 | Dương Khánh Nguyên | API Đăng ký, Đăng nhập |
| Câu 7 | 22110383 | Nguyễn Nhật Nguyên | API Full Category, Last Product |
