import UserTest from "./UserTest";
import LocationTest from "./LocationTest";
import {Button} from "antd";
import Notify from "../notification/Notify";

const TestView = () => {

    const handle = () => {
        Notify.success("Hi");
        Notify.error("Hi");
        Notify.info("Hi");
        Notify.default("Hi");
        Notify.warning("Hi");
    }

    return (
      <div>
          <hr />
          <UserTest />
          <hr />
          <LocationTest />
          <hr />
      </div>
    );

}

export default TestView;