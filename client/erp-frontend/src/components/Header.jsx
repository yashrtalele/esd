import React from "react";

class Header extends React.Component {
    render() {
        return <Head />
    }
}

function Head() {
    return (
        <header className="l-header" id="header">
        <nav className="nav bd-grid">
        </nav>
        </header>
    );
}

export default Header;