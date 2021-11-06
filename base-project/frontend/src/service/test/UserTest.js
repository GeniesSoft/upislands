import {Button} from "antd";
import UserApi from "../user/UserApi";
import TestConstraints from "./TestConstraints";

const UserTest = () => {

    const createUser = () => {
        UserApi.create(TestConstraints.userCreateRequest());
    }
    const getUser = () => {
        UserApi
            .read(1)
            .then(response => console.log(response.data))
            .catch(error => console.log(error));
    }
    const updateUser = () => {
        UserApi.update(TestConstraints.userUpdateRequest())
    }
    const deleteUser = () => {
        UserApi.delete(1)
    }

    return (
        <div>
            <Button onClick={createUser}>Create User</Button>
            <Button onClick={getUser}>Get User</Button>
            <Button onClick={updateUser}>Update User</Button>
            <Button onClick={deleteUser}>Delete User</Button>
        </div>
    );

}

export default UserTest;