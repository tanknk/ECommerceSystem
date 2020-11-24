export default (req, res) => {
  const {
    query: { productID },
  } = req;

  const data = [
    {
      id: 1,
      name: 'Nintendo Switch™ Joy-Con Controllers',
      description: 'Nintendo Switch™ Joy-Con Controllers สินค้าของแท้ กล่องญี่ปุ่น',
      picture: '/images/1/redcyan.jpeg',
      category: {
        id: 1,
        name: 'Gaming',
      },
      shop_id: 1,
      options: [
        {
          id: 1,
          amount: 5,
          name: ' ',
          price: 2490,
          picture: '/images/1/RedCyan.jpeg',
          weight: 0.049,
        },
        {
          id: 2,
          amount: 7,
          name: 'ม่วงส้ม',
          price: 2500,
          picture: '/images/1/PurpleOrange.jpeg',
          weight: 0.049,
        },
        {
          id: 3,
          amount: 0,
          name: 'เขียวชมพู GreenPink',
          price: 2590,
          picture: '/images/1/GreenPink.jpeg',
          weight: 0.049,
        },
      ],
      categorys: [
        {
          id: 1,
          name: 'Gaming',
        },
      ],
    },
    {
      id: 2,
      name: 'Nintendo Switch V2',
      description: 'เครื่องนอกประกันร้าน สินค้าของแท้',
      picture: '/images/2/switch.jpg',
      category: {
        id: 1,
        name: 'Gaming',
      },
      brand: {
        id: 1,
        name: 'Nintendo',
      },
      shop_id: 2,
      options: [
        {
          id: 1,
          amount: 15,
          name: 'ดำ Black',
          price: 10990,
          picture: '/images/2/switch.jpg',
          weight: 0.297,
        },
      ],
      categorys: [
        {
          id: '1',
          name: 'Gaming',
        },
        {
          id: '4',
          name: 'Console',
        },
      ],
    },
  ];

  res.statusCode = 200;

  if (productID === 'all') {
    res.json(data);
  } else {
    res.json(data[productID - 1] ? data[productID - 1] : { error: 'No product data' });
  }
};
