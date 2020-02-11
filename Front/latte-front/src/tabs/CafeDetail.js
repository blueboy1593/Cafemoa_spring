import React from "react";
import {
    Row,
    Col,
    Rate,
    Button,
    Divider,
} from 'antd';
import 'antd/dist/antd.css';
import { Link } from 'react-router-dom';
import { Card, Carousel } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import Review from '../components/Review';

class CafeDetail extends React.Component {

    render() {
        return (
            <Row>
                <Col span={1} />
                <Col span={22}>
                    <Card>
                        <Card.Body>
                            <Row>
                                <Col span={10}>
                                    <Card.Img src="https://www.jeongdong.or.kr/static/portal/img/HKPU_04_04_pic3.jpg" />
                                </Col>
                                <Col span={1} />
                                <Col span={13}>
                                    <Card.Title>카페 이름</Card.Title>
                                    <Card.Subtitle className="mb-2 text-muted">카페 위치 - 역삼동</Card.Subtitle>
                                    <Card.Subtitle className="mb-2 text-muted">평점 <Rate disabled allowHalf defaultValue={2.5} /></Card.Subtitle>
                                    <Card.Text>
                                        Some quick example text to build on the card title and make up the bulk
                                        of the card's content.Some quick example text to build on the card title and make up the bulk
                                        of the card's content.
                                        Some quick example text to build on the card title and make up the bulk
                                        of the card's content.Some quick example text to build on the card title and make up the bulk
                                        of the card's content.
                                    </Card.Text>
                                    <Link to='/visitor/order'>
                                    <Button type="primary">주문하기</Button>
                                    </Link>
                                </Col>
                            </Row>
                        </Card.Body>
                    </Card>

                    <Divider>대표 메뉴</Divider>
                    <div style={{ textAlign: 'center' }}>
                        <Carousel style={{ width: '25rem', display: 'inline-block' }}>
                            <Carousel.Item>
                                <img
                                    className="d-block w-100"
                                    src="https://img.danawa.com/prod_img/500000/055/563/img/3563055_1.jpg?shrink=500:500&_v=20191022163646"
                                    alt="First slide"
                                />
                                <Carousel.Caption>
                                    <h5>음료 이름1</h5>
                                    <p>Nulla vitae elit libero, a pharetra augue mollis interdum.</p>
                                </Carousel.Caption>
                            </Carousel.Item>
                            <Carousel.Item>
                                <img
                                    className="d-block w-100"
                                    src="https://img.danawa.com/prod_img/500000/055/563/img/3563055_1.jpg?shrink=500:500&_v=20191022163646"
                                    alt="Third slide"
                                />
                                <Carousel.Caption>
                                    <h5>음료 이름2</h5>
                                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit.</p>
                                </Carousel.Caption>
                            </Carousel.Item>
                            <Carousel.Item>
                                <img
                                    className="d-block w-100"
                                    src="https://img.danawa.com/prod_img/500000/055/563/img/3563055_1.jpg?shrink=500:500&_v=20191022163646"
                                    alt="Third slide"
                                />

                                <Carousel.Caption>
                                    <h5>음료 이름3</h5>
                                    <p>Praesent commodo cursus magna, vel scelerisque nisl consectetur.</p>
                                </Carousel.Caption>
                            </Carousel.Item>
                        </Carousel>
                    </div>
               
                    {/* 카페 번호를 받아와서 해당 리뷰를 띄워야함  */}
                    <Review></Review>

                </Col>
                <Col span={1} />
            </Row>
        );
    }
}

export default CafeDetail;




// import React from 'react';
// import './Cafe.css';
// import { Button } from 'reactstrap';
// import axios from 'axios';
// import { Link } from 'react-router-dom';

// class CafeDetail extends React.Component{
//       state = {
//     };

//     componentDidMount() {
//       // 지금 당장은 필요 없는 기능!!
//       // const { location, history } = this.props;
//       // if (location.state === undefined) {
//       //   history.push("/visitor");
//       // }
//       const base_url = process.env.REACT_APP_SERVER_IP
//       const ccid = this.props.location.state.id;
//       axios.get(base_url + `/cafe/${ccid}`)
//         .then(response =>{
//           if (response.data.menus[0]) {
//             this.setState({
//               cafe:response.data
//             }); 
//           }
//         });   
//     };

//     render() {
//       const { location } = this.props;
//       const cafe = this.state.cafe;
          
//       if (cafe) {
//         const menus = cafe.menus;
//         return (
//           <center>
//           <div >
//               <div className="CafeDetail">
//                 <div style={{alignItems:"center", padding:"30px"}}>
//                 <img src={location.state.picture} alt={location.state.name} name={location.state.name}/>
//                 </div>
//                 <div>
//                   <h1>카페 이름 : {location.state.name}</h1>
//                   <p>Cafe Description</p>
//                   <p>Cafe Location</p>
//                   <p>사장님 한마디</p>
//                   <Link to={{
//                     pathname: '/customer/order',
//                     state:{
//                       cafe,
//                     }
//                   }}><Button color="info">주문하러 가기</Button>{' '}</Link>
//                 </div>
//               </div>
//               <hr></hr>
//               {menus.map((menu, index) => (
//                 <CafeDetailCard
//                   menu = {menu}
//                   key = {menu.mmid}
//                 />
//               ))}
//               <hr></hr>
//           </div>
//           </center>
//         );
//       } else {
//         return null;
//       }
//     }
// }

// const CafeDetailCard = (props) => {
//   const menu = props.menu
//   return(
//     <div style={{display:"inline-flex", padding:"20px", width:'20rem', margin:"10px"}}>
//       <div className="card" style={{width: '18rem'}}>
//         <img src={menu.mpic} className="card-img-top" alt="..."/>
//         <div className="card-body">
//           <p className="card-text">이름: {menu.mname}</p>
//           <p className="card-text">가격: {menu.menuSize[0].msprice} 원</p>
//         </div>
//       </div>
//     </div>
//   );
// };


// export default CafeDetail;