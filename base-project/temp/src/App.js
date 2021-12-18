import React from 'react';
import "./App.css";
import ComingSoonCard from "./components/Card/ComingSoonCard";

class App extends React.Component {
    render = () => {
        return (
            <ComingSoonCard
                logo={"UpIsle"}
                logoLink={""}
                title={"Work in progress"}
                text={"Website coming soon. Please check back to know."}
                madeBy={"made by GeniesSoft"}
            />
        );
    }
}

export default App;