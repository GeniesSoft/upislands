import {Grid} from "@mui/material";
import LoginForm from "../components/form/login/LoginForm";


const formWrapper = {
    width: "100%,",
    height: "100%",
}

const formStyle = {
    backgroundColor: "whitesmoke",
    margin: "auto",
    marginTop: "20em",
    padding: 50,
    borderRadius: 40,
    boxShadow: "rgba(0, 0, 0, 0.4) 0px 2px 4px, rgba(0, 0, 0, 0.3) 0px 7px 13px -3px, rgba(0, 0, 0, 0.2) 0px -3px 0px inset"

}

const LoginView = () => {

    return (
        <Grid style={formWrapper} container>
            <Grid style={formStyle} item>
                <LoginForm />
            </Grid>
        </Grid>
    );
}

export default LoginView;