import {ReactComponent as IconFacebook} from "../../assets/icons/facebook.svg";
import {ReactComponent as IconTwitter} from "../../assets/icons/twitter.svg";
import React from "react";
import "./ComingSoonCard.css";
import ProgressBar from "../ProgressBar/ProgressBar";

const ComingSoonCard = (props) => {

    return (
        <div className="card">
            <div className="header">
                <div className="logo">
                    <a href={props.logoLink}> {props.logo} </a>
                </div>
                <div className="social">
                    <a href="https://facebook.com" target="_blank">
                        <IconFacebook className="icon"/>
                    </a>
                    <a href="https://twitter.com" target="_blank">
                        <IconTwitter className="icon"/>
                    </a>
                </div>
            </div>
            <div className="content">
                <div className="content-text">
                    <h1> {props.title} </h1>
                    <p> {props.text} </p>
                </div>
                <div className="content-progress">
                    <ProgressBar full={"75"} />
                </div>
            </div>
            <div className="footer">
                <span> {props.madeBy} </span>
            </div>
        </div>
    )

}

export default ComingSoonCard;