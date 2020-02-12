import React from "react";
import {
    Form,
    Input,
    Row,
    Col,
    Button,
    Upload,
    message,
    Icon,
    Radio,
    Divider
} from 'antd';
import 'antd/dist/antd.css';
import axios from 'axios';

function getBase64(img, callback) {
    const reader = new FileReader();
    reader.addEventListener('load', () => callback(reader.result));
    reader.readAsDataURL(img);
}

function beforeUpload(file) {
    const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png';
    if (!isJpgOrPng) {
        message.error('You can only upload JPG/PNG file!');
    }
    const isLt2M = file.size / 1024 / 1024 < 2;
    if (!isLt2M) {
        message.error('Image must smaller than 2MB!');
    }
    return isJpgOrPng && isLt2M;
}

class SignUp extends React.Component {
    state = {
        confirmDirty: false,
        autoCompleteResult: [],
        loading: false
    };

    // 회원가입 버튼 눌렀을 때
    handleSubmit = (e) => {
        e.preventDefault();
        this.props.form.validateFieldsAndScroll((err, values) => {
            if (!err) {
              console.log('Received values of form: ', values);


              const data = {
                uid: values.uid,
                upass: values.upass,
                role: "GUEST",
                uemail: values.uemail,
                uname: "아무거나",
                unickname: "ssafy",
                uphone: values.uphone,
                upic: "nop"
              }
              
              const base_url = process.env.REACT_APP_SERVER_IP
              axios.post(base_url + '/user/signup', data)
              .then(response => {
                  console.log('회원가입')
                  this.props.history.push('/');
              })
              .catch(error => {
                console.log('error')
                console.error(error)
              })
            }
        });
    };

    handleConfirmBlur = (e) => {
        const { value } = e.target;
        this.setState({ confirmDirty: this.state.confirmDirty || !!value });
    };


    compareToFirstPassword = (rule, value, callback) => {
        const { form } = this.props;
        if (value && value !== form.getFieldValue('upass')) {
            callback('비밀번호가 다릅니다.');
        } else {
            callback();
        }
    };

    validateToNextPassword = (rule, value, callback) => {
        const { form } = this.props;
        if (value && this.state.confirmDirty) {
            form.validateFields(['upassConfirm'], { force: true });
        }
        callback();
    };

    handleChange = info => {
        if (info.file.status === 'uploading') {
            this.setState({ loading: true });
            return;
        }
        if (info.file.status === 'done') {
            // Get this url from response in real world.
            getBase64(info.file.originFileObj, imageUrl =>
                this.setState({
                    imageUrl,
                    loading: false,
                }),
            );
        }
    };

    render() {
        const { getFieldDecorator } = this.props.form;

        const formItemLayout = {
            labelCol: {
                xs: { span: 18 },
                sm: { span: 6 },
            },
            wrapperCol: {
                xs: { span: 18 },
                sm: { span: 12 },
            },
        };
        const tailFormItemLayout = {
            wrapperCol: {
                xs: {
                    span: 45,
                    offset: 0,
                },
                sm: {
                    span: 16,
                    offset: 8,
                },
            },
        };
        const uploadButton = (
            <div>
                <Icon type={this.state.loading ? 'loading' : 'plus'} />
                <div className="ant-upload-text">Upload</div>
            </div>
        );

        const { imageUrl } = this.state;

        return (
            <Row>
                <Col span={1} />
                <Col span={22}>
                    <Divider>
                      <Radio.Group name="role" defaultValue={"GUEST"}>
                          <Radio value={"GUEST"}>손님으로 회원가입</Radio>{' '}
                          <Radio value={"HOST"}>사장님으로 회원가입</Radio>
                      </Radio.Group>
                    </Divider>
                    <Form {...formItemLayout} onSubmit={this.handleSubmit}>
                        <Form.Item label="아이디">
                            <Row gutter={8}>
                                <Col span={20}>
                                    {getFieldDecorator('uid', {
                                        rules: [
                                            {
                                                required: true,
                                                message: '아이디를 입력해 주세요!',
                                            },
                                        ],
                                    })(<Input />)}
                                </Col>
                                <Col span={4}>
                                    <Button>중복확인</Button>
                                </Col>
                            </Row>
                        </Form.Item>
                        <Form.Item label="비밀번호" hasFeedback>
                            <Row gutter={8}>
                                <Col span={20}>
                                    {getFieldDecorator('upass', {
                                        rules: [
                                            {
                                                required: true,
                                                message: '비밀번호를 입력해 주세요!',
                                            },
                                            {
                                                validator: this.validateToNextPassword,
                                            },
                                        ],
                                    })(<Input.Password />)}
                                </Col>
                                <Col span={4} />
                            </Row>
                        </Form.Item>
                        <Form.Item label="비밀번호 확인" hasFeedback>
                            <Row gutter={8}>
                                <Col span={20}>
                                    {getFieldDecorator('upassConfirm', {
                                        rules: [
                                            {
                                                required: true,
                                                message: '비밀번호를 입력해 주세요!',
                                            },
                                            {
                                                validator: this.compareToFirstPassword,
                                            },
                                        ],
                                    })(<Input.Password onBlur={this.handleConfirmBlur} />)}
                                </Col>
                                <Col span={4} />
                            </Row>
                        </Form.Item>

                        <Form.Item label="이메일">
                            <Row gutter={8}>
                                <Col span={20}>
                                    {getFieldDecorator('uemail', {
                                        rules: [
                                            {
                                                type: 'email',
                                                message: '이메일 형식이 맞지 않습니다.',
                                            },
                                            {
                                                required: true,
                                                message: '이메일을 입력해 주세요!',
                                            },
                                        ],
                                    })(<Input />)}
                                </Col>
                                <Col span={4}>
                                    <Button>중복확인</Button>
                                </Col>
                            </Row>
                        </Form.Item>

                        <Form.Item label="핸드폰 번호">
                            <Row gutter={8}>
                                <Col span={20}>
                                    {getFieldDecorator('uphone', {
                                        rules: [{ required: true, message: '핸드폰 번호를 입력해 주세요!' }],
                                    })(<Input />)}
                                </Col>
                                <Col span={4} />
                            </Row>
                        </Form.Item>

                        <Form.Item label="프로필 사진">
                            <Upload
                                name="upic"
                                listType="picture-card"
                                className="avatar-uploader"
                                showUploadList={false}
                                action="https://www.mocky.io/v2/5cc8019d300000980a055e76"
                                beforeUpload={beforeUpload}
                                onChange={this.handleChange}
                            >
                                {imageUrl ? <img src={imageUrl} alt="avatar" style={{ width: '100%' }} /> : uploadButton}
                            </Upload>
                        </Form.Item>

                        {/* 손님이면 회원가입, 사장님이면 다음으로 바뀌게 수정 */}
                        <Form.Item {...tailFormItemLayout}>
                            <Button type="primary" htmlType="submit">회원가입</Button>
                        </Form.Item>
                    </Form>
                </Col>
                <Col span={1} />
            </Row>
        );
    }
}
const SignUpBasic = Form.create({ name: 'signup' })(SignUp);

// ReactDOM.render(<SignUpForm />, document.getElementById("react-root"));

export default SignUpBasic;