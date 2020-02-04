import React from 'react';
import './Cafe.css';
import { Button } from 'reactstrap';

class CafeDetail extends React.Component{
    componentDidMount() {
      const { location, history } = this.props;
      if (location.state === undefined) {
        history.push("/");
      }
    }
    render() {
      const { location } = this.props;
      console.log(this.props)
      if (location.state) {
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
                  <Button color="info">주문하러 가기</Button>{' '}
                </div>
              </div>
              <hr></hr>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
              <CafeDetailCard></CafeDetailCard>
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

const CafeDetailCard = () => {
  return(
    <div style={{display:"inline-flex", padding:"20px", width:'20rem', margin:"10px"}}>
      <div className="card" style={{width: '18rem'}}>
        <img src={"https://lh3.googleusercontent.com/proxy/iWzb1dZBZkIyhjM8FrjVH685bqUuG_SMBE37Da0DOclcXBo8iqTb65oeM7yivQcSQo_AeCOW8HgYpGO_M8-ADhv7lxpkzRfIRUCRrMKT7N3lyxjqDRbiiImef6rYPmmPDQFozdb8"} className="card-img-top" alt="..."/>
        <div className="card-body">
          <p className="card-text">흑당 버블티</p>
        </div>
      </div>
    </div>
  );
};


export default CafeDetail;