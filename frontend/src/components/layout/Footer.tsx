import React from 'react';
import { Outlet } from 'react-router-dom';

export const Footer = ({ children }: { children?: React.ReactNode }) => {
  return (
    <div className="footer-wrapper">
      {children || <Outlet />}
    </div>
  );
};
