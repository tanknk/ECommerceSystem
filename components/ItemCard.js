import Link from "next/link";
import { Card, Col } from "react-bootstrap";

const ItemCard = (props) => {
  const { ID, Name, Price, Picture } = props;
  return (
    <Col lg={3} className="mt-2 mb-2" style={{ cursor: "pointer" }}>
      <Link href={`/product/${ID}`}>
        <Card style={{ height: "100%" }}>
          <Card.Img
            variant="top"
            src={Picture}
            style={{
              justifyContent: "center",
              width: "200px",
              height: "150px",
              alignSelf: "center",
              marginTop: 20
            }}
          />
          <Card.Body>
            <Card.Title>{Name}</Card.Title>
            <Card.Text>
              {Price.max !== Price.min ? `฿${Price.min} - ${Price.max}` : `฿${Price.min}`}
            </Card.Text>
          </Card.Body>
        </Card>
      </Link>
    </Col>
  );
};

export default ItemCard;
