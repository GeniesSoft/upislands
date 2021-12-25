import React, {useContext} from 'react';
import {Redirect} from 'react-router-dom';
import {Controller, useForm} from 'react-hook-form';
import {MdLockOpen} from 'react-icons/md';
import {Button, Input, Switch} from 'antd';
import FormControl from 'components/UI/FormControl/FormControl';
import {AuthContext} from 'context/AuthProvider';
import {FieldWrapper, Label, SwitchWrapper} from '../Auth.style';
import UserApi from "../../../service/user/UserApi";

const SignUpForm = () => {
    const {signUp, loggedIn} = useContext(AuthContext);
    const {control, watch, errors, handleSubmit} = useForm({
        mode: 'onChange',
    });
    const password = watch('password');
    const confirmPassword = watch('confirmPassword');
    const onSubmit = (data) => {

        UserApi.create(data);

        console.log(data);

    };
    if (loggedIn) {
        return <Redirect to={{pathname: '/'}}/>;
    }

    return (
        <form onSubmit={handleSubmit(onSubmit)}>

            <FormControl
                label="First Name"
                htmlFor="firstName"
                error={
                    errors.firstName && (
                        <>
                            {errors.firstName?.type === 'required' && (
                                <span>This field is required!</span>
                            )}
                        </>
                    )
                }
            >
                <Controller
                    as={<Input/>}
                    id="firstName"
                    name="firstName"
                    defaultValue=""
                    control={control}
                    rules={{
                        required: true,
                    }}
                />
            </FormControl>

            <FormControl
                label="Last Name"
                htmlFor="lastName"
                error={
                    errors.lastName && (
                        <>
                            {errors.lastName?.type === 'required' && (
                                <span>This field is required!</span>
                            )}
                        </>
                    )
                }
            >
                <Controller
                    as={<Input/>}
                    id="lastName"
                    name="lastName"
                    defaultValue=""
                    control={control}
                    rules={{
                        required: true,
                    }}
                />
            </FormControl>

            <FormControl
                label="Email"
                htmlFor="emailAddress"
                error={
                    errors.emailAddress && (
                        <>
                            {errors.emailAddress?.type === 'required' && (
                                <span>This field is required!</span>
                            )}
                            {errors.emailAddress?.type === 'pattern' && (
                                <span>Please enter a valid email address!</span>
                            )}
                        </>
                    )
                }
            >
                <Controller
                    as={<Input/>}
                    type="email"
                    id="emailAddress"
                    name="emailAddress"
                    defaultValue=""
                    control={control}
                    rules={{
                        required: true,
                        pattern: /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/,
                    }}
                />
            </FormControl>

            <FormControl
                label="Password"
                htmlFor="password"
                error={
                    errors.password && (
                        <>
                            {errors.password?.type === 'required' && (
                                <span>This field is required!</span>
                            )}
                            {errors.password?.type === 'minLength' && (
                                <span>Password must be at lest 6 characters!</span>
                            )}
                            {errors.password?.type === 'maxLength' && (
                                <span>Password must not be longer than 20 characters!</span>
                            )}
                        </>
                    )
                }
            >
                <Controller
                    as={<Input.Password/>}
                    defaultValue=""
                    control={control}
                    id="password"
                    name="password"
                    rules={{required: true, minLength: 6, maxLength: 20}}
                />
            </FormControl>

            {/*<FormControl*/}
            {/*    label="Confirm password"*/}
            {/*    htmlFor="confirmPassword"*/}
            {/*    error={*/}
            {/*        confirmPassword &&*/}
            {/*        password !== confirmPassword && (*/}
            {/*            <span>Your password is not same!</span>*/}
            {/*        )*/}
            {/*    }*/}
            {/*>*/}
            {/*    <Controller*/}
            {/*        as={<Input.Password/>}*/}
            {/*        defaultValue=""*/}
            {/*        control={control}*/}
            {/*        id="confirmPassword"*/}
            {/*        name="confirmPassword"*/}
            {/*    />*/}
            {/*</FormControl>*/}

            <FormControl
                label="Phone"
                htmlFor="phoneNumber"
                error={
                    errors.phoneNumber && (
                        <>
                            {errors.phoneNumber?.type === 'required' && (
                                <span>This field is required!</span>
                            )}
                        </>
                    )
                }
            >
                <Controller
                    as={<Input type="number" />}
                    id="phoneNumber"
                    name="phoneNumber"
                    defaultValue=""
                    control={control}
                    rules={{
                        required: true,
                    }}
                />
            </FormControl>

            <FormControl
                label="Birth Date"
                htmlFor="birthDate"
                error={
                    errors.birthDate && (
                        <>
                            {errors.birthDate?.type === 'required' && (
                                <span>This field is required!</span>
                            )}
                        </>
                    )
                }
            >
                <Controller
                    as={<Input type="date" />}
                    id="birthDate"
                    name="birthDate"
                    defaultValue=""
                    control={control}
                    rules={{
                        required: true,
                    }}
                />
            </FormControl>

            <FieldWrapper>
                <SwitchWrapper>
                    <Controller
                        as={<Switch/>}
                        name="rememberMe"
                        defaultValue={false}
                        valueName="checked"
                        control={control}
                    />
                    <Label>Remember Me</Label>
                </SwitchWrapper>
                <SwitchWrapper>
                    <Controller
                        as={<Switch/>}
                        name="termsAndConditions"
                        defaultValue={false}
                        valueName="checked"
                        control={control}
                    />
                    <Label>I agree with terms and conditions</Label>
                </SwitchWrapper>
            </FieldWrapper>
            <Button
                className="signin-btn"
                type="primary"
                htmlType="submit"
                size="large"
                style={{width: '100%'}}
            >
                <MdLockOpen/>
                Register
            </Button>
        </form>
    );
};

export default SignUpForm;
