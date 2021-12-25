import HomeView from "./views/HomeView";
import UsersView from "./views/UsersView";
import CompaniesView from "./views/CompaniesView";
import LocationsView from "./views/LocationsView";
import LocalGuideView from "./views/LocalGuideView";
import BookingsView from "./views/BookingsView";
import HomeIcon from '@mui/icons-material/Home';
import PeopleIcon from '@mui/icons-material/People';
import BusinessIcon from '@mui/icons-material/Business';
import MyLocationIcon from '@mui/icons-material/MyLocation';
import BookIcon from '@mui/icons-material/Book';
import BookmarkIcon from '@mui/icons-material/Bookmark';


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
    {
        id: 4,
        name: "Local Guide",
        path: "/local-guide",
        component: <LocalGuideView/>,
        icon: <BookIcon/>,
    },
    {
        id: 5,
        name: "Booking",
        path: "/bookings",
        component: <BookingsView />,
        icon: <BookmarkIcon />,
    },

]