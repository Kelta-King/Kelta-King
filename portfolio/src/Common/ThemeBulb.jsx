import React from 'react';

const ThemeBulb = () => {
    return (
        <div style={{
            position: 'fixed',
            top: "2px",
            zIndex: 100000,
            left: "2px",
        }}
        >
            <p onClick={() => console.log("yo")}>
                Light bulb here
            </p>
        </div>
    );
}

export default ThemeBulb;
