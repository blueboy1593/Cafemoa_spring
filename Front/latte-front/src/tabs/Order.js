import React, { Component } from 'react'

export default class Order extends Component {
  render() {
    return (
      <div>
        <h1>`( name )` 카페의 주문하기 페이지</h1>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
          <CafeDetailCard></CafeDetailCard>
      </div>
    )
  }
}

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