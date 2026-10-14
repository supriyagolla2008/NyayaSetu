import React from 'react';

export const Modal = ({ children, className = '' }: { children?: React.ReactNode, className?: string }) => {
  return <div className={`modal ${className}`}>{children}</div>;
};
