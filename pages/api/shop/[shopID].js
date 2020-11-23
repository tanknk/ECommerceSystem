export default (req, res) => {
  const {
    query: { shopID },
  } = req;

  const data = [
    {
      id: 1,
      sales_id: 1,
      description: "ขายอุปกรณ์เกม",
      shopName: "Game & Toy Shop",
      total_sales: "20",
      // image: '/images/shop/1/shop.png',
    },
    {
      id: 2,
      sales_id: 2,
      description: "ขาย Console หลากหลายชนิด",
      shopName: "GMK Shop",
      total_sales: "10",
      // image: '/images/shop/2/shop.png',
    },
  ];

  res.statusCode = 200;
  let obj;
  for (let i = 0; i < data.length; i++) {
    if (data[i].id == shopID) {
      obj = data[i]
      break;
    }
  }
  res.json(obj ? obj : { error: "No shop data" });
};
