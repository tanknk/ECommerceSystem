import Head from 'next/head';
import Link from 'next/link';

import {
  Form, Button, Col, Row,
} from 'react-bootstrap';

const login = () => (
  <div>
    <Head>
      <title>Login | E-Commerce</title>
      <link rel="icon" type="image/png" href="/favicon.png" />
    </Head>
    <Col>
      <Row>
        <Form className="mx-auto mt-5">
          <h3 className="text-center mb-5">เข้าสู่ระบบเพื่อใช้งาน</h3>
          <Form.Group controlId="formBasicEmail">
            <Form.Label>อีเมล</Form.Label>
            <Form.Control type="email" placeholder="อีเมล" />
          </Form.Group>

          <Form.Group controlId="formBasicPassword">
            <Form.Label>รหัสผ่าน</Form.Label>
            <Form.Control type="password" placeholder="รหัสผ่าน" />
          </Form.Group>
          <Link href="/list">
            <Button variant="primary" type="submit">
              เข้าสู่ระบบ
            </Button>
          </Link>
        </Form>
      </Row>
    </Col>
  </div>
);

export default login;
