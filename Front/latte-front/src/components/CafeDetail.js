import React from 'react';
import './Cafe.css';
import { Button } from 'reactstrap';
import axios from 'axios';
import { Link } from 'react-router-dom';

class CafeDetail extends React.Component{
      state = {
        // cafe: {}
        // cafe: {
        //   cname : "Hollys",
        //   cloc : "yeoksam",
        //   cphone: 212451153,
        //   cpic: "https://image.chosun.com/sitedata/image/201706/15/2017061501890_1.jpg",
        //   copen: '0900',
        //   cclose: 1800,
        //   cdesc: "description",
        //   cstatus: 0,
        //   coperation: 0,
        //   menus: [
        //     {
        //       "menuSize": [
        //         {
        //           "msname": "S",
        //           "msprice": 3000
        //         },
        //      {
        //           "msname": "M",
        //           "msprice": 3500
        //         },
        //      {
        //           "msname": "L",
        //           "msprice": 4000
        //         }
        //       ],
        //       "mname": "오렌지주스",
        //       "mpic": "https://sc01.alicdn.com/kf/HTB1WPBcklDH8KJjy1zeq6xjepXav/Fruit-juice-production-line-juice-filling-machine.jpg_350x350.jpg"
        //     },
            
        //     {
        //       "menuSize": [
        //         {
        //           "msname": "S",
        //           "msprice": 2000
        //         },
        //      {
        //           "msname": "M",
        //           "msprice": 2500
        //         },
        //      {
        //           "msname": "L",
        //           "msprice": 3000
        //         }
        //       ],
        //       "mname": "카페라떼",
        //       "mpic": "https://snaptime.edaily.co.kr/wp-content/uploads/2019/07/tyle-hws-01-1562802184-700x700.png"
        //     }
        //   ]}
    };

    componentDidMount() {
      // 지금 당장은 필요 없는 기능!!
      // const { location, history } = this.props;
      // if (location.state === undefined) {
      //   history.push("/visitor");
      // }
      const ccid = this.props.location.state.id;
      axios.get(`http://i02a301.p.ssafy.io:8080/latte/cafe/${ccid}`)
        .then(response =>{
          if (response.data.menus[0]) {
            this.setState({
              cafe:response.data
            }); 
          }
        });   
    };

    render() {
      const { location } = this.props;
      const cafe = this.state.cafe;
          
      if (cafe) {
        const menus = cafe.menus;
        return (
          <center>
          <div >
              <div className="CafeDetail">
                <div style={{alignItems:"center", padding:"30px"}}>
                <img src={location.state.picture} alt={location.state.name} name={location.state.name}/>
                </div>
                <div>
                  <h1>카페 이름 : {location.state.name}</h1>
                  <p>Cafe Description</p>
                  <p>Cafe Location</p>
                  <p>사장님 한마디</p>
                  <Link to={{
                    pathname: '/customer/order',
                    state:{
                      cafe,
                    }
                  }}><Button color="info">주문하러 가기</Button>{' '}</Link>
                </div>
              </div>
              <hr></hr>
              {menus.map((menu, index) => (
                <CafeDetailCard
                  menu = {menu}
                  key = {menu.mmid}
                />
              ))}
          </div>
          </center>
        );
      } else {
        return null;
      }
    }
}

// const card_style = {
//   opacity: 1,
//   display: 'block',
//   backgroundColor: 'white',
// }

const CafeDetailCard = (props) => {
  const menu = props.menu
  return(
    <div style={{display:"inline-flex", padding:"20px", width:'20rem', margin:"10px"}}>
      <div className="card" style={{width: '18rem'}}>
        <img src={menu.mpic} className="card-img-top" alt="..."/>
        <div className="card-body">
          <p className="card-text">이름: {menu.mname}</p>
          <p className="card-text">가격: {menu.menuSize[0].msprice} 원</p>
        </div>
      </div>
    </div>
  );
};


export default CafeDetail;