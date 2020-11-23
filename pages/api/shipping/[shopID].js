export default (req, res) => {
  const {
    query: { shopID },
  } = req;

  const data = [
    {
      id: 1,
      shop_id: 1,
      name: "Kerry",
      price: 30,
      time_estimate: 2,
    },
    {
      id: 2,
      shop_id: 1,
      name: "DHL",
      price: 30,
      time_estimate: 2,
    },
    {
      id: 3,
      shop_id: 1,
      name: "Best Express",
      price: 30,
      time_estimate: 2,
    },
    {
      id: 4,
      shop_id: 2,
      name: "Shopee Express",
      price: 30,
      time_esimate: 3,
    },
    {
      id: 5,
      shop_id: 1,
      name: "Ninja Van",
      price: 30,
      time_esimate: 3,
    },
  ];
  let data_json = [];

  res.statusCode = 200;
  for (let i = 0; i < data.length; i++) {
    if (data[i].shop_id == shopID) {
      data_json.push(data[i]);
    }
  }
  res.json(
    data_json !== [] ? data_json : { error: "No shipping data" }
  );
};
