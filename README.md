# E-CommerceSystem
ผลงานนี้เป็นส่วนหนึ่งของรายวิชา การโปรแกรมเชิงบริการ (06016325) คณะเทคโนโลยีสารสนเทศ สถาบันเทคโนโลยีพระจอมเกล้าเจ้าคุณทหารลาดกระบัง

## รายชื่อสมาชิก
กลุ่ม S.O.Pang (กลุ่มการเรียนที่ 2 อังคารบ่าย)
1. นางสาวเกศราณี ไตรสวัสดิ์วงศ์ (61070018)
2. นายคุณากร โฆสิตสกุล (61070020)
3. นางสาวฉัตรธิดา แจ้งใจ (61070029)
4. นายชาญวิทย์ เศรษฐวงศ์สิน (61070040)
5. นายนรรณจา โสวรรณ (61070097)

## รายละเอียดของงาน
กลุ่ม S.O.Pang ได้รับผิดชอบจัดทำในส่วนของ Product Module ซึ่งเป็นส่วนหนึ่งของระบบ E-Commerce โดยได้ทำการแบ่งออกเป็น Service ย่อย ๆ ดังนี้
* การจัดการกับรายการสินค้า
  * เพิ่มรายการสินค้า
  * ดูรายละเอียดรายการสินค้า
  * แก้ไขรายละเอียดรายการสินค้า
  * ลบรายการสินค้า
  <!-- end of the list -->
และนอกจากนี้ยังได้จัดทำ Swagger ซึ่งสามารถใช้งานได้จริงควบคู่ไปพร้อมกับหน้าเว็บไซต์จำลอง


## Service Diagram (Product)
![Image of Service Diagram](https://github.com/tanknk/ECommerceSystem/blob/main/images/service.png)

## วิธีการใช้งาน
ในการทดสอบการใช้งานกับหน้าเว็บไซต์จำลองจำเป็นที่จะต้องรัน Front End และ Swagger ไปในเวลาเดียวกัน เพื่อให้หน้าเว็บไซต์สามารถดึง API จาก Swagger ได้
### Front End
1. type **yarn install** in terminal
2. run the development server with this commind in terminal:
```bash
npm run dev
# or
yarn dev
```
3. Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

### Swagger
1. import ProductModule as Project
2. Run ProductApp.java
3. Open [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html) with your browser to see the result.

## API List
* สร้างรายการสินค้า (createProduct)
* สร้างหมวดหมู่ของรายการสินค้า (createCategory)
* สร้างตัวเลือกของรายการสินค้า (createOption)
* ลบรายการสินค้า (deleteProduct)
* ลบตัวเลือกของรายการสินค้า (deleteOption)
* ลบรูปภาพของตัวเลือกของรายการสินค้า (deletePicture)
* ดึงข้อมูลสินค้าด้วยรหัสรายการสินค้า (getProductById)
* ดึงข้อมูลตัวเลือกของรายการสินค้าด้วยรหัสรายการสินค้า (getOptionsฺbyProductId)
* ดึงข้อมูลรายการสินค้าทั้งหมด (getAllProduct)
* ดึงข้อมูลรายการสินค้าด้วยรหัสร้านค้า (getProductByShopId)
* ดึงข้อมูลรายการสินค้าด้วยรหัสหมวดหมู่ (getProductByCategoryId)
* แก้ไขข้อมูลรายการสินค้า (updateProduct)
* แก้ไขข้อมูลตัวเลือกของรายการสินค้า (updateOption)
* แก้ไขหมวดหมู่ของรายการสินค้า (updateCategory)
