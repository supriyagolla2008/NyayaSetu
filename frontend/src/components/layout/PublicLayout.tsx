import React from 'react';
import { Outlet } from 'react-router-dom';

export const PublicLayout = ({ children }: { children?: React.ReactNode }) => {
  return (
    <div className="publiclayout-wrapper">
      {children || <Outlet />}
    </div>
  );
};
