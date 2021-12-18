import React from "react";
import "./ProgressBar.css";

const ProgressBar = (props) => {

    return (
        <div className="meter"> <span style={{width: `${props.full}%`}} /> </div>
    )

}

export default ProgressBar;