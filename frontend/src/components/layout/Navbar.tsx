import React from 'react';
import { Outlet } from 'react-router-dom';

export const Navbar = ({ children }: { children?: React.ReactNode }) => {
  return (
    <div className="navbar-wrapper">
      {children || <Outlet />}
    </div>
  );
};
