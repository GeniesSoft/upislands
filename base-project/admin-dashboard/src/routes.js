import HomeView from "./views/HomeView";
import UsersView from "./views/UsersView";
import CompaniesView from "./views/CompaniesView";
import LocationsView from "./views/LocationsView";
import HomeIcon from '@mui/icons-material/Home';
import PeopleIcon from '@mui/icons-material/People';
import BusinessIcon from '@mui/icons-material/Business';
import MyLocationIcon from '@mui/icons-material/MyLocation';

export const routes = [
    {
        id: 0,
        name: "Home",
        path: "/",
        component: <HomeView/>,
        icon: <HomeIcon/>,
    },
    {
        id: 1,
        name: "Users",
        path: "/users",
        component: <UsersView/>,
        icon: <PeopleIcon/>,
    },
    {
        id: 2,
        name: "Companies",
        path: "/companies",
        component: <CompaniesView/>,
        icon: <BusinessIcon/>,
    },
    {
        id: 3,
        name: "Locations",
        path: "/locations",
        component: <LocationsView/>,
        icon: <MyLocationIcon/>,
    },

]