# E-CommerceSystem
ผลงานนี้เป็นส่วนหนึ่งของรายวิชา การโปรแกรมเชิงบริการ (06016325) คณะเทคโนโลยีสารสนเทศ สถาบันเทคโนโลยีพระจอมเกล้าเจ้าคุณทหารลาดกระบัง
<br><br>**ข้อแนะนำในการใช้งาน**
1. ควรศึกษาวิธีการใช้งานบริเวณด้านล่างให้เข้าใจอย่างถี่ถ้วน
2. ภายใน Git Repo นี้ประกอบไปด้วย 2 ส่วน ได้แก่ เว็บไซต์และโค้ดภาษา Java ที่ใช้ในการเข้าถึง Swagger โดยโค้ดภาษา Java ที่ใช้ในการเข้าถึง Swagger จะอยู่ภายในโฟลเดอร์ที่ชื่อว่า ProductModule ที่เป็น Maven Project
3. ในกรณีที่ต้องการทดสอบกับหน้าเว็บไซต์ มีความจำเป็นที่จะต้องรันทั้งเว็บไซต์และ ProductApp.java (จะได้เฉพาะสิ่งที่เกี่ยวข้องกับ Product Module โดยในส่วนของ Module อื่น ๆ ที่เกี่ยวข้อง เช่น Shipping Shop หรือ Review นั้น คณะผู้จัดทำได้ใช้ข้อมูลจำลองที่ได้เตรียมไว้ในการแสดงผล ซึ่งมีรูปแบบที่เหมือนกับ API ที่ Module นั้น ๆ เตรียมไว้ จึงสามารถเปลี่ยน Path เพื่อไปดึง API มาจาก Module นั้น ๆ ได้ โดยไม่มีข้อผิดพลาดใด ๆ)

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

# วิธีการใช้งาน (สำคัญ)
**ในการทดสอบการใช้งานกับหน้าเว็บไซต์จำลองจำเป็นที่จะต้องรัน Website และ Swagger ไปในเวลาเดียวกัน เพื่อให้หน้าเว็บไซต์สามารถดึง API จาก Swagger ได้**
โดยที่ภายใน Github Repo นี้จะประกอบไปด้วย Maven Project ที่ใช้ในการเข้าถึง Swagger และหน้าเว็บไซต์ที่จัดทำด้วย Next.js จึงต้องมีการรัน 2 ส่วนแยกจากกัน
ได้แก่
## Website
0. Open this project as folder (Visual Studio Code)
1. type **yarn install** in terminal
2. run the development server with this commind in terminal:
```bash
npm run dev
# or
yarn dev
```
3. Open [http://localhost:3000](http://localhost:3000) with your browser to see the result.

## Swagger
**โฟลเดอร์ที่มีชื่อว่า ProductModule คือ Maven Project ที่ใช้ในการเข้าถึง Swagger ที่จัดการกับ Service ต่าง ๆ ของ Product**
1. import ProductModule as Project (Eclipse)
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

## Module อื่น ๆ ที่เกี่ยวข้อง
* [Module Shop](https://github.com/Peechanok/Group-Lamp-SOP)
* [Module Shipping](https://github.com/AnTznimalz/SOP_ShippingModule)
* [Module Review](https://github.com/looksocii/SOP_API)
