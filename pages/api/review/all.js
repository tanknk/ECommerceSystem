export default (req, res) => {
  const data = [
    {
      review_id: 1,
      rank: 5,
      content: 'ดีมากครับ',
      photo: '/review/1/review.jpg',
      product_id: 1,
      user_id: 'user1',
    },
    {
      review_id: 2,
      rank: 4,
      content: 'สีสวยมาก ถูกใจเลย',
      photo: '/review/1/review.jpg',
      product_id: 1,
      user_id: 'user2',
    },
    {
      review_id: 3,
      rank: 1,
      content: 'กล่องขาด เหมือนโดนแกะ',
      photo: '/review/1/review.jpg',
      product_id: 1,
      user_id: 'user3',
    },
  ];

  res.statusCode = 200;
  res.json(data);
};
